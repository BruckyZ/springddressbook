package com.example.springaddressbook;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController
{
	@Autowired
	AddressRepository addressRepository;


	@RequestMapping("/")
	public String listAddress(Model model)
	{
		model.addAttribute("addresses", addressRepository.findAll());
		return "list";
	}

	@GetMapping("/add")
	public String addressForm(Model model)
	{
		model.addAttribute("address", new Address());
		return "addressform";
	}

	@PostMapping("/process")
	public String processForm(@Valid Address address, BindingResult result)
	{
		if(result.hasErrors())
		{
			return "addressform";
		}
		addressRepository.save(address);
		return "redirect:/";
	}

	@RequestMapping("/detail/{id}")
	public String showaddress(@PathVariable("id") long id, Model model)
	{
		model.addAttribute("address", addressRepository.findOne(id));
		return "show";
	}

	@RequestMapping("/update/{id}")
	public String updateddress(@PathVariable("id") long id, Model model)
	{
		model.addAttribute("address", addressRepository.findOne(id));
		return "addressform";
	}

	@RequestMapping("/delete/{id}")
	public String deleteaddress(@PathVariable("id") long id)
	{
		addressRepository.delete(id);
		return "redirect:/";
	}
}
