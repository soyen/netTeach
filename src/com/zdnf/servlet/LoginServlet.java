package com.zdnf.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

import org.json.*;

import com.zdnf.dao.IAdminDAO;
import com.zdnf.dao.IStudentDAO;
import com.zdnf.dao.ITeacherDAO;
import com.zdnf.model.Admin;
import com.zdnf.model.Student;
import com.zdnf.model.Teacher;
import com.zdnf.servlet.base.BaseServlet;
import com.zdnf.util.Constant;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
//@WebServlet(urlPatterns="/android/login.jsp")
public class LoginServlet extends BaseServlet
{
    public void service(HttpServletRequest request ,
		HttpServletResponse response)
		throws IOException , ServletException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		int id = 0;
		
		switch (Integer.parseInt(userType)) {
		case 1:
			// 获取studentDAO
			IStudentDAO studentDAO = (IStudentDAO)getCtx().getBean("studentDAO");
			// 验证用户登录			
			Student student = studentDAO.validate(username, password);
			// 登录成功
			if (student != null)
			{
				id = student.getId();
				request.getSession(true).setAttribute("id" , id);
			}
			break;
		case 2:
			// 获取studentDAO
			ITeacherDAO teacherDAO = (ITeacherDAO)getCtx().getBean("teacherDAO");
			// 验证用户登录			
			Teacher teacher = teacherDAO.validate(username, password);
			// 登录成功
			if (teacher != null)
			{
				id = teacher.getId();
				request.getSession(true).setAttribute("id" , id);
			}		
			break;
		case 3:
			// 获取adminDAO
			IAdminDAO adminDAO = (IAdminDAO)getCtx().getBean("adminDAO");
			// 验证用户登录			
			Admin admin = adminDAO.validate(username, password);
			// 登录成功
			if (admin != null)
			{
				id = admin.getId();
				request.getSession(true).setAttribute("id" , id);
			}
	        break;

		}		
		response.setContentType("text/html; charset=GBK");		
		try
		{
			// 把验证的userId封装成JSONObject
			JSONObject jsonObj = new JSONObject().put("id" , id);

			// 输出响应
			response.getWriter().println(jsonObj.toString());
		}
		catch (JSONException ex)
		{
			ex.printStackTrace();
		}
	}
}