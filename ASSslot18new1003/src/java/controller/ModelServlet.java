/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ModelDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Model;

/**
 *
 * @author Admin
 */
public class ModelServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ModelServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModelServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("view")) {
            ArrayList<Model> ListModels = ModelDAO.getModels();
            request.setAttribute("ListModels", ListModels);
            request.getRequestDispatcher("view/ModelManager.jsp").forward(request, response);
        } else if (action.equals("add")) {
            request.getRequestDispatcher("view/addModel.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            String id = request.getParameter("id");
            int modelid = Integer.parseInt(id);
            Model model = ModelDAO.getModelByModelId(modelid);
            request.setAttribute("modelid", modelid);
            request.setAttribute("modelname", model.getModelName());
            request.getRequestDispatcher("view/editModel.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            String id = request.getParameter("id");
            int modelid = Integer.parseInt(id);
            Model model = ModelDAO.getModelByModelId(modelid);
            ModelDAO.deleteModel(model);

            ArrayList<Model> ListModels = ModelDAO.getModels();
            request.setAttribute("ListModels", ListModels);
            request.getRequestDispatcher("view/ModelManager.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("add")) {
            String modelname = request.getParameter("modelname");
            ArrayList<Model> ListModels = ModelDAO.getModels();
            for (Model model : ListModels) {
                if (model.getModelName().equals(modelname)) {
                    request.setAttribute("error", "Model " + modelname + " exist");
                    request.getRequestDispatcher("view/addModel.jsp").forward(request, response);
                    return;
                }
            }
            Model model = new Model(modelname);
            ModelDAO.addModel(model);
            ListModels = ModelDAO.getModels();
            request.setAttribute("ListModels", ListModels);
            request.getRequestDispatcher("view/ModelManager.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            String modelname = request.getParameter("modelname");
            int modelid = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("modelid", modelid);
            Model oldmodel = ModelDAO.getModelByModelId(modelid);
            ArrayList<Model> ListModels = ModelDAO.getModels();
            if (!oldmodel.getModelName().equals(modelname)) {
                for (Model model : ListModels) {
                    if (model.getModelName().equals(modelname)) {
                        request.setAttribute("error", "Model " + modelname + " exist");
                        request.setAttribute("modelname", modelname);
                        request.getRequestDispatcher("view/editModel.jsp").forward(request, response);
                        return;
                    }
                }
            }
            Model model = new Model(modelid, modelname);
            ModelDAO.updateModel(model);
            ListModels = ModelDAO.getModels();
            request.setAttribute("ListModels", ListModels);
            request.getRequestDispatcher("view/ModelManager.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
