package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.models.model;
import com.example.demo.repository.modelrepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class modelcontroller {
	@Autowired
	modelrepository rep;
	@GetMapping("/")
	public String home(Model m) {
		List<model> l = (List<model>) rep.findAll();
		m.addAttribute("add_products",l);
		return "home";
	}
	@GetMapping("/getbyid/{id}")
	public String getbyid(@PathVariable(value = "id") int id,Model m) {
		Optional<model> ml = rep.findById(id);
		model mo = ml.get();
		m.addAttribute("products",mo);
		return "edit";
	}
	@PostMapping("/save_products")
	public String insert(@ModelAttribute model m,HttpSession session) {
		rep.save(m);
		session.setAttribute("message", "successfullyadded");
		return "Redirect:/loadform";
	}
	@PutMapping("/update")
	public String edit(@ModelAttribute model m, HttpSession session) {
		rep.save(m);
		session.setAttribute("message", "successfullyupdated");
		return "Redirect:/";
	}
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id")int id, HttpSession session) {
		rep.deleteById(id);
		session.setAttribute("message", "successfullydeleted");
		return "Redirect:/";
	}
	@GetMapping("/loadform")
	public String loadform() {
		return "add";
	}
}
