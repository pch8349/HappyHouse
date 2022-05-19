package com.ssafy.vue.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.Hospital;
import com.ssafy.vue.dto.Interest;
import com.ssafy.vue.dto.Shop;
import com.ssafy.vue.service.InterestService;


@RestController
@RequestMapping("/interest")
public class InterestController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private InterestService interestService;

	@PostMapping()
	public ResponseEntity<String> register(@RequestBody Interest interest) throws Exception {
		if(interestService.insert(interest)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@GetMapping("{id}")
	public ResponseEntity<List<Interest>> list(@PathVariable String id) throws Exception {
		return new ResponseEntity<List<Interest>>(interestService.selectAllById(id), HttpStatus.OK);
	}

//	@GetMapping("/modifyInterest")
//	public String modify(Model model, HttpSession session, @RequestParam("interestIdx") int interestIdx,
//			RedirectAttributes redirectAttributes) {
//		User user = (User) session.getAttribute("loginUser");
//
//		if (user != null) {
//			try {
//				Interest interest = interestService.selectOne(interestIdx);
//				model.addAttribute("interest", interest);
//				return "interest/modifyInterest";
//			} catch (Exception e) {
//				e.printStackTrace();
//				redirectAttributes.addAttribute("msg", "관심지역 얻기중 에러가 발생했습니다.");
//				return "redirect:/interest/interestList";
//			}
//		} else {
//			return "user/login";
//		}
//	}

	@PutMapping("{idx}")
	public ResponseEntity<String> modify(@RequestBody Interest interest) throws Exception {
		if (interestService.update(interest)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("{idx}")
	public ResponseEntity<String> delete(@PathVariable int idx) throws Exception {
		if (interestService.delete(idx)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("showShop/{sidoName}/{gugunName}")
	public ResponseEntity<List<Shop>> retrieveShop(@PathVariable String sidoName, @PathVariable String gugunName) throws Exception {
		return new ResponseEntity<List<Shop>>(interestService.selectShop(sidoName, gugunName), HttpStatus.OK);
	}
	
	@GetMapping("showHospital/{sidoName}/{gugunName}")
	public ResponseEntity<List<Hospital>> retrieveHospital(@PathVariable String sidoName, @PathVariable String gugunName) throws Exception {
		return new ResponseEntity<List<Hospital>>(interestService.selectHospital(sidoName, gugunName), HttpStatus.OK);
	}
	
//	@GetMapping("/showHospitalShop")
//	public String showHospitalShop(Model model, HttpSession session, RedirectAttributes redirectAttributes, @RequestParam("sidoName") String sidoName, @RequestParam("gugunName") String gugunName) {
//		User user = (User) session.getAttribute("loginUser");
//		
//		if (user != null) {
//			try {
//				String list = interestService.showHospitalShop(sidoName, gugunName);
//				redirectAttributes.addFlashAttribute("hslist", list);
//				System.out.println(list);
//				return "redirect:/interest/interestList";
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				model.addAttribute("msg", "주변 상권, 병원 정보가 없습니다.");
//				return "index";
//			}
//		} else {
//			return "user/login";
//		}
//	}
//}
}