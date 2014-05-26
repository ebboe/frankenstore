/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;
import beans.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Ebbe
 */
public class StoreServlet extends HttpServlet {
    private static String loginPage = null;
    private static String createUserPage = null;
    private static String frankenlistPage = null;
    private static String adminPage = null;
    private static String jdbcURL = null;
    private BodyPartListBean bodyPartList = null;
    private FrankenListBean frankenList = null;
        
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        loginPage = config.getInitParameter("LOGIN_USER_PAGE");
        createUserPage = config.getInitParameter("CREATE_USER_PAGE");
        frankenlistPage = config.getInitParameter("FRANKENLIST_PAGE");
        adminPage = config.getInitParameter("ADMIN_PAGE");
        
        jdbcURL = config.getInitParameter("JDBC_URL");
        
        try {
            frankenList = new FrankenListBean();
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        
        try {
            bodyPartList = new BodyPartListBean(jdbcURL);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    
    public void destroy() {
        
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        ShoppingCartBean shoppingBean = getCart(request);
        session.setAttribute("jdbcURL", jdbcURL);
        
        if (request.getParameter("action").equals("login")) {
            ProfileBean user = new ProfileBean(jdbcURL);
            
            try {
                user.getUser((String)request.getParameter("username"));
            } catch (Exception e) {
                throw new ServletException("Error loading profile", e);
            }
            session.setAttribute("profile", user);
            
            
            response.sendRedirect(frankenlistPage);
        } else if (request.getParameter("action").equals("logout")) {
            
            session.removeAttribute("jdbcURL");
            session.removeAttribute("profile");
            session.removeAttribute("shoppingCart");
            session.invalidate();
            
            response.sendRedirect(loginPage);
        } else if (request.getParameter("action").equals("create_user")) {
            response.sendRedirect(createUserPage);
        } else if (request.getParameter("action").equals("usercreate")) {
            ProfileBean createuser = new ProfileBean(jdbcURL);
            
            createuser.setUsername(request.getParameter("user"));
            if (request.getParameter("role") == null) {
                createuser.setRole("USER");
            }
            else {
                createuser.setRole("ADMIN");
            }

            try {
                createuser.insertUser();
            } catch (Exception e) {
                throw new ServletException("Error saving user profile", e);
            }
            session.setAttribute("profile", createuser);
            
            response.sendRedirect(loginPage);
        } else if (request.getParameter("action").equals("update_user")) {
            
            ProfileBean updateuser = (ProfileBean) session.getAttribute("profile");
            
            updateuser.setUsername(request.getParameter("user"));
            if (request.getParameter("role") == null) {
                updateuser.setRole("USER");
            }
            else {
                updateuser.setRole("ADMIN");
            }

            try {
                updateuser.updateProfile();
            } catch (Exception e) {
                throw new ServletException("Error saving user profile", e);
            }
            session.setAttribute("profile", updateuser);
            
            response.sendRedirect(frankenlistPage);
            
        } else if (request.getParameter("action").equals("add_to_cart")) {
            
            int frankenid = Integer.parseInt(request.getParameter("frankenid"));
            FrankenBean addFranken = frankenList.getFrankenBean(frankenid);
            
            shoppingBean.addFranken(addFranken);
            
            response.sendRedirect(frankenlistPage);
        } else if (request.getParameter("action").equals("remove_from_cart")) {
            int frankenid = Integer.parseInt(request.getParameter("frankenid"));
            FrankenBean removeFranken = frankenList.getFrankenBean(frankenid);
            
            shoppingBean.removeFranken(removeFranken);
            response.sendRedirect(frankenlistPage);
        } else if (request.getParameter("action").equals("checkout")) {
            
            ProfileBean profile = (ProfileBean) session.getAttribute("profile");
            
            try {
                profile.checkout(shoppingBean);
            } catch (Exception ex) {
                Logger.getLogger(StoreServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            //session.removeAttribute("shoppingCart");
            response.sendRedirect(frankenlistPage);
        } else if (request.getParameter("action").equals("create_new_franken")) {
            FrankenBean newFranken = new FrankenBean(jdbcURL);
            session.setAttribute("franken", newFranken);
            session.setAttribute("bodyPartList", bodyPartList);
           response.sendRedirect(adminPage);
        } else if (request.getParameter("action").equals("add_new_part")) {
           FrankenBean newFranken = (FrankenBean)session.getAttribute("franken");
           String frankenName = request.getParameter("frankenName");
           newFranken.setName(frankenName);
           
           int bodyPartId = Integer.parseInt(request.getParameter("bodypart"));
           BodyPartBean bodyPart = null;
           
            try {
                bodyPart = new BodyPartBean(bodyPartId);
            } catch (Exception ex) {
                Logger.getLogger(StoreServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            newFranken.addBodyPart(bodyPart);
           
           session.setAttribute("bodyPartList", bodyPartList);
           session.setAttribute("franken", newFranken);
           response.sendRedirect(adminPage);
        } else if (request.getParameter("action").equals("save_franken")) {
           FrankenBean franken = (FrankenBean)session.getAttribute("franken");
           
            try {
                franken.save();
            } catch (Exception ex) {
                Logger.getLogger(StoreServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           response.sendRedirect(frankenlistPage);
        }
    }
    
    private ShoppingCartBean getCart(HttpServletRequest request) {
        HttpSession se = request.getSession();
        
        ShoppingCartBean cart = (ShoppingCartBean)se.getAttribute("shoppingCart");
        
        if (cart == null) {
            cart = new ShoppingCartBean();
            se.setAttribute("shoppingCart", cart);
        }
        
        return cart;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
