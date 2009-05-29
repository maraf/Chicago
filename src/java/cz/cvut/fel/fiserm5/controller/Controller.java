/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.fiserm5.controller;

import cz.cvut.fel.fiserm5.javabeans.*;
import cz.cvut.fel.fiserm5.tools.HracComparator;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Marek SMM
 */
public class Controller extends HttpServlet {

    private int generateRandom1to6() {
        return (int) (Math.random() * 6) + 1;
    }

    private void insertPlayer(Hrac h) {
        DataSource dataSource = null;
        try {
            Context c = new InitialContext();
            dataSource = (DataSource) c.lookup("java:comp/env/jdbc/mysql");
        } catch (NamingException ex) {
            System.err.println("WARN: Nenalezen zdroj.");
        }

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO `chicago_game`(`name`, `score`) VALUES (\"" + h.getName() + "\", " + h.getScore() + ");");
        } catch (SQLException ex) {
            System.err.println("WARN: Nelze ziskat spojeni nebo vytvorit statement.");
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.err.println("ERR: Nelze zavrit spojeni s DB.");
            }
        }
    }

    private ArrayList<Hrac> selectPlayers() {
        ArrayList<Hrac> hraci = new ArrayList<Hrac>();
        DataSource dataSource = null;
        try {
            Context c = new InitialContext();
            dataSource = (DataSource) c.lookup("java:comp/env/jdbc/mysql");
        } catch (NamingException ex) {
            System.err.println("WARN: Nenalezen zdroj.");
        }

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `name`, `score` FROM `chicago_game` ORDER BY `score`;");
            while(rs.next()) {
                Hrac h = new Hrac();
                h.setName(rs.getString("name"));
                h.setScore(rs.getInt("score"));
                hraci.add(h);
            }
        } catch (SQLException ex) {
            System.err.println("WARN: Nelze ziskat spojeni nebo vytvorit statement.");
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.err.println("ERR: Nelze zavrit spojeni s DB.");
            }
        }
        
        Collections.sort(hraci, new HracComparator(HracComparator.DESCENDING));
        return hraci;
    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(request.getParameter("kostka1"));

        /*

        - nejdrive zobrazit view pro zadani hracova jmena, vytvorit session a hrace v ni.
        - zdedit z Hrac.java a doplnit pro aktualniho jeste kolik ma odhazeno a skore v soucasnem kole.
        - dodelat ukladani a zobrazovani vysledku (r.128)


         */

        String token = String.valueOf(Math.random() * 1000) + String.valueOf(Math.random() * 1000);
        String viewPath = "/welcomeView.jsp";
        Integer howMany = 0;
        AktualniHrac aktualniHrac;
        boolean[] kostky = new boolean[3];
        int cisla[] = new int[3];

        ArrayList<Hrac> hraci = (ArrayList<Hrac>) this.getServletContext().getAttribute("hraci");
        if (hraci == null) {
            hraci = selectPlayers();
            this.getServletContext().setAttribute("hraci", hraci);
        }

        HttpSession session = request.getSession();
        if (!session.isNew() && session.getAttribute("aktualniHrac") != null) {
            aktualniHrac = (AktualniHrac) session.getAttribute("aktualniHrac");
            viewPath = "/view.jsp";
        } else {
            aktualniHrac = new AktualniHrac();
            cisla[0] = this.generateRandom1to6();
            cisla[1] = this.generateRandom1to6();
            cisla[2] = this.generateRandom1to6();
            aktualniHrac.setNumbers(cisla);
        }
        if(session.isNew()) {
            howMany = 10;
            session.setAttribute("howMany", howMany);
        }

        String t1 = (String) session.getAttribute("token");
        String t2 = (String) request.getParameter("token");
        if (t1 != null && t2 != null && t1.equals(t2)) {
            if (request.getParameter("sendName") != null && request.getParameter("sendName").equals("Uloz")) {
                String name = request.getParameter("name");
                if(name != null && !name.equals("")) {
                    aktualniHrac.setName(name);
                    session.setAttribute("aktualniHrac", aktualniHrac);
                    viewPath = "/view.jsp";
                } else {
                    request.setAttribute("errorWelcomeMessage", "Musite zadat neprazdne jmeno");
                }
            } else if (request.getParameter("logout") != null) {
                session.removeAttribute("aktualniHrac");
                aktualniHrac = new AktualniHrac();
                viewPath = "/welcomeView.jsp";
            } else if (request.getParameter("again") != null) {
                String jmeno = aktualniHrac.getName();
                aktualniHrac = new AktualniHrac();
                aktualniHrac.setName(jmeno);
                cisla[0] = this.generateRandom1to6();
                cisla[1] = this.generateRandom1to6();
                cisla[2] = this.generateRandom1to6();
                aktualniHrac.setNumbers(cisla);
                session.setAttribute("aktualniHrac", aktualniHrac);
                viewPath = "/view.jsp";
            } else if (request.getParameter("topn") != null) {
                try {
                    howMany = (Integer) session.getAttribute("howMany");
                    Integer howManyNew = Integer.valueOf(request.getParameter("howMany"));
                    if (howManyNew != null && howMany != howManyNew) {
                        if(howManyNew < 1) {
                            request.setAttribute("errorMessage", "Cislo musi byt vetsi jak nula!");
                        } else {
                            howMany = howManyNew;
                            session.setAttribute("howMany", howMany);
                        }
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Musite zadat cislo!");
                }
            }

            if (request.getParameter("hazej") != null) {
                for (int i = 0; i < 3; i++) {
                    if (request.getParameter("kostka" + (i + 1)) != null && request.getParameter("kostka" + (i + 1)).equals("on")) {
                        kostky[i] = true;
                        cisla[i] = this.generateRandom1to6();
                    } else {
                        int[] tmp = aktualniHrac.getNumbers();
                        cisla[i] = tmp[i];
                        kostky[i] = false;
                    }
                }
                aktualniHrac.setCubes(kostky);

                if (aktualniHrac.getRound() < 7) {
                    if (aktualniHrac.getInThisRound() > 1) {
                        aktualniHrac.setInThisRound(0);
                        aktualniHrac.setRound(aktualniHrac.getRound() + 1);
                        aktualniHrac.setScore(aktualniHrac.getScore() + aktualniHrac.getInThisRoundPoints());
                        cisla[0] = this.generateRandom1to6();
                        cisla[1] = this.generateRandom1to6();
                        cisla[2] = this.generateRandom1to6();
                        aktualniHrac.setNumbers(cisla);
                        kostky[0] = kostky[1] = kostky[2] = true;
                        aktualniHrac.setCubes(kostky);
                    } else {
                        aktualniHrac.setInThisRound(aktualniHrac.getInThisRound() + 1);
                        aktualniHrac.setNumbers(cisla);
                        int skore = 0;
                        for (int i = 0; i < 3; i++) {
                            switch (cisla[i]) {
                                case 1:
                                    skore += 100;
                                    break;
                                case 6:
                                    skore += 60;
                                    break;
                                default:
                                    skore += cisla[i];
                            }
                        }
                        aktualniHrac.setInThisRoundPoints(skore);
                    }
                }
                if (aktualniHrac.getRound() == 7 && aktualniHrac.getInThisRound() == 0) {
                    viewPath = "/finishView.jsp";
                    Hrac h = new Hrac();
                    h.setName(aktualniHrac.getName());
                    h.setScore(aktualniHrac.getScore());
                    hraci.add(h);
                    insertPlayer(h);
                    Collections.sort(hraci, new HracComparator(HracComparator.DESCENDING));
                }
            }
            if (aktualniHrac.getRound() == 7 && aktualniHrac.getInThisRound() == 0) {
                viewPath = "/finishView.jsp";
            }
        } else {
            if (aktualniHrac.getRound() == 7 && aktualniHrac.getInThisRound() == 0) {
                viewPath = "/finishView.jsp";
            }
        }

        request.setAttribute("aktualniHrac", aktualniHrac);
        request.setAttribute("hraci", hraci);
        request.setAttribute("token", token);
        request.setAttribute("action", response.encodeURL(request.getContextPath() + "/game"));
        session.setAttribute("token", token);

        request.setAttribute("controllerSet", "abcd1234efgh5678");

        try {
            RequestDispatcher rd = request.getRequestDispatcher(viewPath);
            rd.forward(request, response);
        } catch (Exception e) {
            System.err.println("Neporadil se forward!!!");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
