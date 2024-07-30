package com.Execution2.controller;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Execution2.models.Cards;
import com.Execution2.models.CardsDto;
import com.Execution2.models.Carousel;
import com.Execution2.models.CarouselDto;
import com.Execution2.models.Navbar;
import com.Execution2.models.Navbar2;
import com.Execution2.services.CardsRepository;
import com.Execution2.services.CarouselRepository;
import com.Execution2.services.Navbar2Repository;
import com.Execution2.services.NavbarRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/navbar")
public class Navbarcontroller {

		@Autowired
		
		private NavbarRepository repo;
		
		@Autowired
		private Navbar2Repository repo1;
		
		@Autowired
		private CarouselRepository repo2;
		
		@Autowired
		private CardsRepository repo3;
		
		
		@GetMapping({"","/"})
		public String showNavbarList(Model model) {
			List<Navbar> navbar = repo.findAll();
			model.addAttribute("navbar", navbar);
			return "navbar/display";
					
		}
		@GetMapping({"/display2"})
		public String showNavbar2List(Model model) {
			List<Navbar2> navbar2 = repo1.findAll();
			model.addAttribute("navbar2", navbar2);
			return "navbar/display2";
					
		}
		@GetMapping({"/display3"})
		public String Cards(Model model) {
			List<Cards> cards = repo3.findAll();
			model.addAttribute("cards", cards);
			return "navbar/display3";			
		}
		@GetMapping({"/display4"})
		public String Carousell(Model model) {
			List<Carousel> carousel = repo2.findAll();
			model.addAttribute("carousel", carousel);
			return "navbar/display4";			
		}
		@GetMapping({"/cards"})
		public String Card(Model model) {
			List<Cards> cards = repo3.findAll();
			model.addAttribute("cards", cards);
			return "navbar/cards";			
		}
		@GetMapping({"/carousel"})
		public String Carousel(Model model) {
			List<Carousel> carousel = repo2.findAll();
			model.addAttribute("carousel", carousel);
			return "navbar/carousel";			
		}
		@GetMapping({"/nav"})
		public String showNavList(Model model) {
			List<Navbar> navbar = repo.findAll();
			model.addAttribute("navbar", navbar);
			List<Navbar2> navbar2 = repo1.findAll();
			model.addAttribute("navbar2", navbar2);
			List<Carousel> carousel = repo2.findAll();
			model.addAttribute("carousel", carousel);
			List<Cards> cards = repo3.findAll();
			model.addAttribute("cards", cards);
			return "navbar/nav";
}
		@GetMapping("/insert")
		public String showCreatePage(Model model) {
			CardsDto cardsDto = new CardsDto();  //Object object = new Object();
			model.addAttribute("CardsDto",cardsDto);
			return "navbar/insert";
		}
		
		@PostMapping("/insert")
		public String createCards(
				@Valid @ModelAttribute CardsDto cardsDto,
				BindingResult result
				) {
			if(cardsDto.getImageFile().isEmpty()) {
				result.addError(new FieldError("cardsDto","imageFile","The image file is required"));
			}
			
			if(result.hasErrors()) {
				return "navbar/insert";
			}
			
	//saving the image file
			MultipartFile image = cardsDto.getImageFile();
				Date joiningDate = new Date();
				String storageFileName = joiningDate.getTime()+"_"+image.getOriginalFilename();
				
				try {
					String uploadDir = "public/images/";
					Path uploadPath = Paths.get(uploadDir);
					
					if(!Files.exists(uploadPath)) {
						Files.createDirectories(uploadPath);
					}
				
				try(InputStream inputStream = image.getInputStream()){
					Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
							StandardCopyOption.REPLACE_EXISTING);
				}
			}catch(Exception ex) {
				System.out.println("Exception : " + ex.getMessage());
			}
				
				
				Cards cards = new Cards();
				cards.setMname(cardsDto.getMname());
				cards.setPrice(cardsDto.getPrice());
				cards.setImageFile(storageFileName);
				
				repo3.save(cards);
			
			return "redirect:/navbar/display3";
		}
		
		@GetMapping("/edit")
		public String showEditPage(
				Model model,
				@RequestParam int id
				) {
			try {
			    Cards cards = repo3.findById(id).get();
				model.addAttribute("cards",cards);
				
				CardsDto cardsDto = new CardsDto();
				cardsDto.setMname(cards.getMname());
				cardsDto.setPrice(cards.getPrice());
				
				model.addAttribute("cardsDto",cardsDto);
				

			}
			catch(Exception ex) {
				System.out.println("Exception: " + ex.getMessage());
				return "redirect:/navbar";
			}
			return "navbar/edit";
		}
		
		@PostMapping("/edit")
		public String updateCards(
				Model model,
				@RequestParam int id,
				@Valid @ModelAttribute CardsDto cardsDto,
				BindingResult result
				) {
			
			try {
				Cards cards = repo3.findById(id).get();
				model.addAttribute("cards",cards);
				
				if(result.hasErrors()) {
					return "navbar/edit";
				}
				
				if(!cardsDto.getImageFile().isEmpty()) {
					//for deleting the old images
					String uploadDir = "public/images/";
					Path oldImagePath = Paths.get(uploadDir + cards.getImageFile());
					try {
						Files.delete(oldImagePath);
					}
					catch(Exception ex) {
						System.out.println("Exception: " + ex.getMessage());
					}
					
					//save the new image
					MultipartFile image = cardsDto.getImageFile();
					Date joiningDate = new Date();
					String storageFileName = joiningDate.getTime()+"_"+image.getOriginalFilename();
					
					try(InputStream inputStream = image.getInputStream()){
						Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
								StandardCopyOption.REPLACE_EXISTING);
				}
					cards.setImageFile(storageFileName);
			}
				cards.setMname(cardsDto.getMname());
				cards.setPrice(cardsDto.getPrice());

				repo3.save(cards);
			}
			catch(Exception ex) {
				System.out.println("Exception: " + ex.getMessage());
			}
			
			
			return "redirect:/navbar/display3";
		}

		@GetMapping("/delete")
		public String deleteCards(
			@RequestParam int id
			) {
			try {
				Cards cards = repo3.findById(id).get();
				
				//for deleting the product image
				Path imagePath = Paths.get("public/images/" + cards.getImageFile());
				
				try {
					Files.delete(imagePath);
				}
				catch(Exception ex) {
					System.out.println("Exception: " + ex.getMessage());
				}
				
				//to delete the product
				repo3.delete(cards);
			}
			catch(Exception ex) {
				System.out.println("Exception: " + ex.getMessage());
			}
			return "redirect:/navbar/display3";
		}
}