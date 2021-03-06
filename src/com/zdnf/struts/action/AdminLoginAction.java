/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zdnf.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zdnf.dao.IAdminDAO;
import com.zdnf.model.Admin;
import com.zdnf.struts.form.AdminLoginForm;


public class AdminLoginAction extends Action {
  
	private IAdminDAO adminDAO;
	
	public void setAdminDAO(IAdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		AdminLoginForm adminForm = (AdminLoginForm) form;
		String username = adminForm.getName();
		String password = adminForm.getPassword();
		Admin admin = adminDAO.validate(username, password);
        if(admin!=null){
        	session.setAttribute("user", admin);
			session.setAttribute("userName",username);
        	return mapping.findForward("success");
        }else{
        	String error = "��¼ʧ��";
             request.setAttribute("msg", error);
        	return mapping.findForward("error");
        }
	}
}