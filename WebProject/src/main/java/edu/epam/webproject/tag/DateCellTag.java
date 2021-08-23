package edu.epam.webproject.tag;

import edu.epam.webproject.util.DateUtil;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Date;

/**
 * Class functions of custom tag
 */
public class DateCellTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger();
    private Date date;
    private boolean isEnable;

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int doStartTag() {
        try {
            JspWriter out = pageContext.getOut();
            String tagClass;
            if (isEnable){
                tagClass = "date-cell-enable";
                out.write("<div class=\"date-cell "+ tagClass + "\" onclick=\"setArrivalDate()\">"+ DateUtil.getDayOfMonth(date)+ "." + (DateUtil.getMonth(date) + 1) + "</div>");
            }else{
                tagClass = "date-cell-disable";
                out.write("<div class=\"date-cell "+ tagClass + "\">"+ DateUtil.getDayOfMonth(date)+ "." + (DateUtil.getMonth(date) + 1) + "</div>");
            }


        } catch (IOException exception) {
            logger.log(Level.ERROR, "Error while writing to out stream for tag", exception);
        }
        return SKIP_BODY;
    }
}
