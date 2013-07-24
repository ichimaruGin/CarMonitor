package org.zju.carmonitor.server;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author jiezhen 7/23/13
 */
public class XmlServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(XmlServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req != null){
            resp.setCharacterEncoding("UTF-8");
            String para = req.getParameter("param");
            logger.debug("URL Param: " + para);
//            ArrayList<ValueObject> voList = new ArrayList<ValueObject>();
//            switch (URL_PARAM.valueOf(para)){
//                case COMPANY_URL_PARAM: voList = cashierComDaoImpl.getAllCashierCompany(); break;
//                case CASHIER_URL_PARAM: voList = cashierDaoImpl.getAllCashier(); break;
//                case CASH_URL_PARAM: voList = cashDaoImpl.getAllCash(); break;
//                case BRAND_URL_PARAM: voList = cashierBrandDaoImpl.getAllCashierBrand(); break;
//                default: logger.debug("Unrecognized url :"+para);
//            }
//            resp.getWriter().write(transVoListToString(voList));
            resp.flushBuffer();
        }
    }

//    private String transVoListToString(ArrayList<ValueObject> list){
//        StringBuilder sb = new StringBuilder();
//        if (list != null && list.size() > 0){
//            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//            sb.append("<List>");
//            for (Iterator<ValueObject> iter = list.iterator(); iter.hasNext();){
//                ValueObject vo = iter.next();
//                sb.append("<Record>");
//                sb.append(vo.toXML());
//                logger.debug(vo.toXML());
//                sb.append("</Record>");
//            }
//        }
//        sb.append("</List>");
//        return sb.toString();
//
//    }
}
