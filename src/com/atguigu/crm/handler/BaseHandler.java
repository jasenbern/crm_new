package com.atguigu.crm.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Navigation;

@Controller
public class BaseHandler {

	@ResponseBody
	@RequestMapping("/navigate")
	public List<Navigation> navigation(HttpSession session) {

		User user = (User) session.getAttribute("user");
		String contextPath = session.getServletContext().getContextPath();

		List<Navigation> navigations = new ArrayList<>();

		Navigation root = new Navigation(Long.MAX_VALUE, "客户关系管理系统");
		root.setState("opened");
		navigations.add(root);

		List<Authority> userAuths = user.getRole().getAuthorities();

		Map<String, Navigation> pns = new HashMap<>();
		for (Authority subAuth : userAuths) {
			Navigation sNavi = new Navigation(subAuth.getId(),
					subAuth.getDisplayName());
			sNavi.setUrl(contextPath + subAuth.getUrl());

			Authority parentAuth = subAuth.getParentAuthority();
			Navigation pNavi = pns.get(parentAuth.getDisplayName());

			if (pNavi == null) {
				pNavi = new Navigation(parentAuth.getId(),
						parentAuth.getDisplayName());
				pNavi.setState("opened");

				root.getChildren().add(pNavi);

				pns.put(parentAuth.getDisplayName(), pNavi);
			}

			pNavi.getChildren().add(sNavi);
		}

		return navigations;
	}
}
