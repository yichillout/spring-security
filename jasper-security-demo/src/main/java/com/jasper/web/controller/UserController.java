package com.jasper.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jasper.dto.User;
import com.jasper.dto.UserQueryCondition;
import com.jasper.exception.UserNotExistException;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author jasper
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	// @RequestMapping(value = "/user", method = RequestMethod.GET)
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value = "users search service")
	public List<User> query(
			@RequestParam(name = "username", required = false, defaultValue = "Jasper") String username) {

		System.out.println("username : " + username);

		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}

	// @RequestMapping(value = "/user/{id:[\\d]+}", method = RequestMethod.GET)
	@GetMapping("/{id:[\\d]+}")
	@JsonView(User.UserDetailView.class)
	public User getuUserInfo(@PathVariable(name = "id") String id) {

		if (Integer.parseInt(id) != 2) {
			throw new UserNotExistException(Integer.parseInt(id));
		}

		User user = new User();
		user.setUsername("James");
		return user;
	}

	@PostMapping
	public User create(@Valid @RequestBody User user, BindingResult errors) {
		// without the BindingResult, spring will block the not valid request and not
		// continue the following statements.
		// test post : return status code
		// public User create(@Valid @RequestBody User user) { //

		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(e -> System.out.println(e.getDefaultMessage()));
		}

		System.out.println("post receive : username " + user.getId());
		System.out.println("post receive : username " + user.getUsername());
		System.out.println("post receive : password " + user.getPassword());
		System.out.println("post receive : birthday " + user.getBirthday());
		user.setId(1);
		return user;
	}

	@PutMapping("/{id:[\\d]+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {

		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(e -> {
				// FieldError fieldError = (FieldError) e;
				// String message = fieldError.getField() + " : error : " +
				// fieldError.getDefaultMessage();
				// System.out.println(message);
				System.out.println(e.getDefaultMessage());

			});
		}

		System.out.println("post receive : username " + user.getId());
		System.out.println("post receive : username " + user.getUsername());
		System.out.println("post receive : password " + user.getPassword());
		System.out.println("post receive : birthday " + user.getBirthday());
		user.setId(1);
		return user;
	}

	@DeleteMapping("/{id:[\\d]+}")
	public void delete(@PathVariable String id) {
		System.out.println(id + " is deleted");
	}

	@GetMapping("/timefilter")
	@JsonView(User.UserDetailView.class)
	public String queryFilter() throws InterruptedException {
		System.out.println("time filter test");
		Thread.sleep(1000);
		return "time filter test";
	}

	@RequestMapping(value = "/condition", method = RequestMethod.GET)
	public void query(UserQueryCondition condition,
			@PageableDefault(page = 1, size = 15, sort = "username") Pageable pageable) {

		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());
	}
}
