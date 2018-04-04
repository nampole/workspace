package controller;

import logic.Shop;
import logic.User;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginFormController {

	private Shop shopService;

	private Validator loginValidator;

	public void setShopService(Shop shopService) {
		this.shopService = shopService;
	}

	public void setLoginValidator(Validator loginValidator) {
		this.loginValidator = loginValidator;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String toLoginView() {
		return "login";
	}

	@ModelAttribute
	public User setUpForm() {
		return new User();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(User user, BindingResult bindingResult) {

		this.loginValidator.validate(user, bindingResult);

		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}

		try {
			// 유저 정보 검색
			User loginUser = this.shopService.getUserByUserIdAndPassword(user.getUserId(), user.getPassword());

			// 유저가 있을 때
			modelAndView.setViewName("loginSuccess");
			modelAndView.addObject("loginUser", loginUser);
			return modelAndView;
		} catch (EmptyResultDataAccessException e) {
			// 유저가 없을 때
			bindingResult.reject("error.login.user");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
	}
}