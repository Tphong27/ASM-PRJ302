/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BrandDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Brand;

/**
 *
 * @author Admin
 */
public class BrandServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BrandServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BrandServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
<<<<<<< HEAD
        if (action == null) {
//            request.getRequestDispatcher("view/BrandManager.jsp").forward(request, response);
        } else if (action.equals("view")) {
=======
        if (action.equals("view")) {
>>>>>>> 9b0e173 (Cập nhật code)
            ArrayList<Brand> ListBrands = BrandDAO.getBrands();
            request.setAttribute("ListBrands", ListBrands);
            request.getRequestDispatcher("view/BrandManager.jsp").forward(request, response);
        } else if (action.equals("add")) {
            request.getRequestDispatcher("view/addBrand.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            String id = request.getParameter("id");
            int brandid = Integer.parseInt(id);
            Brand brand = BrandDAO.getBrandByBrandId(brandid);
            request.setAttribute("brandid", brandid);
            request.setAttribute("brandname", brand.getBrandName());
            request.getRequestDispatcher("view/editBrand.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            String id = request.getParameter("id");
            int brandid = Integer.parseInt(id);
            Brand brand = BrandDAO.getBrandByBrandId(brandid);
            BrandDAO.deleteBrand(brand);
<<<<<<< HEAD
            
=======

>>>>>>> 9b0e173 (Cập nhật code)
            ArrayList<Brand> ListBrands = BrandDAO.getBrands();
            request.setAttribute("ListBrands", ListBrands);
            request.getRequestDispatcher("view/BrandManager.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
<<<<<<< HEAD
        if (action == null) {
//            request.getRequestDispatcher("view/BrandManager.jsp").forward(request, response);
        } else if (action.equals("add")) {
=======

        if (action.equals("add")) {
>>>>>>> 9b0e173 (Cập nhật code)
            String brandName = request.getParameter("brandname");
            ArrayList<Brand> ListBrands = BrandDAO.getBrands();
            for (Brand brand : ListBrands) {
                if (brand.getBrandName().equals(brandName)) {
                    request.setAttribute("error", "brand exist");
                    request.getRequestDispatcher("view/addBrand.jsp").forward(request, response);
                    return;
                }
            }
            Brand brand = new Brand(brandName);
            BrandDAO.addBrand(brand);
            ListBrands = BrandDAO.getBrands();
            request.setAttribute("ListBrands", ListBrands);
            request.getRequestDispatcher("view/BrandManager.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            String brandName = request.getParameter("brandname");
            String id = request.getParameter("id");
            int brandid = Integer.parseInt(id);
            request.setAttribute("brandid", brandid);
            Brand oldbrand = BrandDAO.getBrandByBrandId(brandid);
            ArrayList<Brand> ListBrands = BrandDAO.getBrands();
            if (!oldbrand.getBrandName().equals(brandName)) {
                for (Brand brand : ListBrands) {
                    if (brand.getBrandName().equals(brandName)) {
                        request.setAttribute("error", "brand " + brandName + " exist");
                        request.setAttribute("brandname", brandName);
                        request.getRequestDispatcher("view/editBrand.jsp").forward(request, response);
                        return;
                    }
                }
            }
            Brand brand = new Brand(brandid, brandName);
            BrandDAO.updateBrand(brand);
            ListBrands = BrandDAO.getBrands();
            request.setAttribute("ListBrands", ListBrands);
            request.getRequestDispatcher("view/BrandManager.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
