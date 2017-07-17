package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.HikesDAO;
import data.HikesDaoImpl;

@Controller
public class HikesController {
	@Autowired
	private HikesDAO dao;

	@RequestMapping(path = "route.do", params = "name", method=RequestMethod.GET)
	public ModelAndView getHikeByName(@RequestParam("name") String name) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("HikesView.jsp");
		mv.addObject("hike", dao.getHikeByName(name));
		return mv;
	}
	
	@RequestMapping(path = "seeAllHikes.do", method=RequestMethod.GET)
	public ModelAndView seeAllHikes() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("seeAllHikes.jsp");
		mv.addObject("allHikes", dao.getAllHikes());
		return mv;
	}

	
}
