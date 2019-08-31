package com.mvc.first;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Helloworldcontroller {

	@RequestMapping("/show")
	public String showform()
	{
		
		return "hello-world";
	}
	@RequestMapping("/processform")
	public String processform()
	{
		return "processed";
	}
	/*@RequestMapping("/editor")
	public String Capitalizer(HttpServletRequest request,Model model)
	{
		String name=request.getParameter("studentname");
		name=name.toUpperCase();
		String result="Hello  "+name;
		model.addAttribute("message",result);
		
		return "processed";
	}*/
	@RequestMapping("/editor")
	public String Greeting(@RequestParam("studentname") String name,Model model)
	{
		name=name.toUpperCase();
		String result="Hey "+name+" How are you";
		model.addAttribute("greetings",result);
		return "processed";
		
	}
}
