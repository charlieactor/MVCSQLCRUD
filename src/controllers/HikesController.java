package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import data.Hike;
import data.HikesDaoDBImpl;

@Controller
@SessionAttributes("allHikes")
public class HikesController {
	@Autowired
	private HikesDaoDBImpl dao = new HikesDaoDBImpl();

	@ModelAttribute("allHikes")
	public List<Hike> initAllHikes() {
		return dao.getAllHikes();
	}

	@RequestMapping(path = "route.do", params = "name", method = RequestMethod.GET)
	public ModelAndView getHikeByName(@RequestParam("name") String name) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("HikesView.jsp");
		mv.addObject("hike", dao.getHikeByName(name));
		mv.addObject("pictures", dao.getPicturesForHike(dao.getHikeByName(name)));
		return mv;
	}

	@RequestMapping(path = "seeAllHikes.do", method = RequestMethod.GET)
	public ModelAndView seeAllHikes(@ModelAttribute("allHikes") List<Hike> hikes) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("seeAllHikes.jsp");
		mv.addObject("allHikes", dao.getAllHikes());
		return mv;
	}

	@RequestMapping(path = "route.do", params = "length", method = RequestMethod.GET)
	public ModelAndView getHikesByLength(@RequestParam("length") String lengthStr) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sortedHikes.jsp");
		double length;
		if (lengthStr != null && !lengthStr.equals("")) {
			length = Double.parseDouble(lengthStr);
		} else {
			length = 0;
		}
		mv.addObject("sortedHikes", dao.getListOfHikesByLength(length));
		return mv;
	}

	@RequestMapping(path = "route.do", params = "difficulty", method = RequestMethod.GET)
	public ModelAndView getHikesByDifficulty(@RequestParam("difficulty") String difficulty) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sortedHikes.jsp");
		if (difficulty == null) {
			difficulty = "";
		}

		mv.addObject("sortedHikes", dao.getListOfHikesByDifficulty(difficulty));
		return mv;

	}

	@RequestMapping(path = "route.do", params = "distance", method = RequestMethod.GET)
	public ModelAndView getHikesByDistance(@RequestParam("distance") String distanceStr) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sortedHikes.jsp");
		double distance;
		if (distanceStr != null && !distanceStr.equals("")) {
			distance = Double.parseDouble(distanceStr);
		} else {
			distance = 0;
		}
		mv.addObject("sortedHikes", dao.getListOfHikesByDistanceFromDenver(distance));
		return mv;
	}

	@RequestMapping(path = "route.do", params = { "name", "difficulty", "length", "distanceFromDenver",
			"fact", "picture" }, method = RequestMethod.POST)
	public ModelAndView addHike(@RequestParam("name") String name, @RequestParam("difficulty") String difficulty,
			@RequestParam("length") String lengthStr, @RequestParam("distanceFromDenver") String distanceStr,
			@RequestParam("fact") String fact, @RequestParam("picture") String picture, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		double length;
		double distance;
		if (lengthStr != null && !lengthStr.equals("")) {
			length = Double.parseDouble(lengthStr);
		} else {
			length = 0;
		}
		if (distanceStr != null && !distanceStr.equals("")) {
			distance = Double.parseDouble(distanceStr);
		} else {
			distance = 0;
		}
		Hike h = new Hike(name, length, difficulty, distance, fact, 0);
		dao.addHike(h);
		dao.addPicture(h.getId(), picture);
		redir.addFlashAttribute("hike", dao.getHikeByName(name));
		mv.setViewName("redirect:seeAllHikes.do");
		return mv;
	}

	@RequestMapping(path = "editHikes.do", method = RequestMethod.GET)
	public ModelAndView editHikesList(@ModelAttribute("allHikes") List<Hike> hikes) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editHikes.jsp");
		mv.addObject("allHikes", hikes);
		return mv;
	}

	@RequestMapping(path = "editSingleHike.do", params = "hikeToEdit", method = RequestMethod.GET)
	public ModelAndView editSingleHike(@RequestParam("hikeToEdit") String hike) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editSingleHike.jsp");
		mv.addObject("hike", dao.getHikeByName(hike));
		return mv;
	}

	@RequestMapping(path = "updateHike.do", params = { "name", "difficulty", "length", "distance", "fact",
			"id", "picture" }, method = RequestMethod.POST)
	public ModelAndView editSingleHike(@RequestParam("name") String name, @RequestParam("difficulty") String difficulty,
			@RequestParam("length") String lengthStr, @RequestParam("distance") String distanceStr,
			@RequestParam("fact") String fact, @RequestParam("id") int id, @RequestParam("picture") String picture, RedirectAttributes redir) {
		double length, distance;
		ModelAndView mv = new ModelAndView();
		if (lengthStr != null && !lengthStr.equals("")) {
			length = Double.parseDouble(lengthStr);
		} else {
			length = 0;
		}
		if (distanceStr != null && !distanceStr.equals("")) {
			distance = Double.parseDouble(distanceStr);
		} else {
			distance = 0;
		}
		Hike updatedHike = new Hike(name, length, difficulty, distance, fact, id);
		dao.updateHike(updatedHike);
		if (picture != null && !picture.equals("")) {
			dao.addPicture(updatedHike.getId(), picture);
		}
		redir.addFlashAttribute("hike", updatedHike);
		mv.setViewName("redirect:seeAllHikes.do");
		return mv;
	}

	@RequestMapping(path = "deleteHike.do", params = "name", method = RequestMethod.POST)
	public ModelAndView deleteHike(@RequestParam("name") String name, @ModelAttribute("allHikes") List<Hike> hikes,
			RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		dao.removeHike(dao.getHikeByName(name));
		mv.setViewName("redirect:seeAllHikes.do");
		redir.addFlashAttribute("allHikes", hikes);
		return mv;
	}

}
