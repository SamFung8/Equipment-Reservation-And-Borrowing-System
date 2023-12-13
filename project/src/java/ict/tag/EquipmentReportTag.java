/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;


import ict.bean.EquipmentReportBean;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author sam
 */
public class EquipmentReportTag extends SimpleTagSupport {

    ArrayList<EquipmentReportBean> er = new ArrayList();

    public void setEr(ArrayList<EquipmentReportBean> er) {
        this.er = er;
    }

    @Override
    public void doTag() throws IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        
        for (int i = 0; i < er.size(); i++) {
            EquipmentReportBean erb = er.get(i);
            out.println("<tr>");
            out.println("<td>" + erb.getEquipmentID() + "</td>");
            out.println("<td>" + erb.getName() + "</td>");
            out.println("<td>" + erb.getCount() + "</td>");
            out.println("</tr>");
        }
    }
}
