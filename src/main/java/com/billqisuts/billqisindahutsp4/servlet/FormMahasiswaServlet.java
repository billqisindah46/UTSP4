package com.billqisuts.billqisindahutsP4.servlet;

import com.billqisuts.billqisindahutsP4.dao.MahasiswaDao;
import com.billqisuts.billqisindahutsP4.database.KoneksiDatabase;
import com.billqisuts.billqisindahutsP4.model.Mahasiswa;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dieslow
 */
public class FormMahasiswaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nim = request.getParameter("nim");
        String nama = request.getParameter("nama");
        String tmpt = request.getParameter("tmpt");
        String tgl = request.getParameter("tgl");
        String jk = request.getParameter("jk");
        String alamat = request.getParameter("alamat");

        try {
            Date tgllahir = new SimpleDateFormat("dd/MM/yyyy").parse(tgl);
            String driver = "oracle.jdbc.driver.OracleDriver";
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String user = "belajar";
            String pass = "orcl";

            KoneksiDatabase conn = new KoneksiDatabase(driver, url, user, pass);
            MahasiswaDao md = new MahasiswaDao(conn);
            Mahasiswa mhs = new Mahasiswa();

            mhs.setId(1);
            mhs.setNim(nim);
            mhs.setNama(nama);
            mhs.setTempatLahir(tmpt);
            mhs.setTglLahir(tgllahir);
            mhs.setJenisKelamin(jk);
            mhs.setAlamat(alamat);
            md.simpan(mhs);
        } catch (ParseException ex) {
            Logger.getLogger(FormMahasiswaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("nim", nim);
        request.setAttribute("nama", nama);
        request.setAttribute("tmpt", tmpt);
        request.setAttribute("tgl", tgl);
        request.setAttribute("jk", jk);
        request.setAttribute("alamat", alamat);
        request.setAttribute("waktu", new Date());
        request.getRequestDispatcher("WEB-INF/templates/jsp/ViewMahasiswa.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
