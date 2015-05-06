package cz.muni.fi.pa036.betting.extensions;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import net.sourceforge.stripes.format.Formatter;

public class JodaTimeFormatter implements Formatter<DateTime> {

    private String formatType;

    public static final DateTimeFormatter fmtDate = DateTimeFormat.forPattern("yyyy-MM-dd");
    public static final DateTimeFormatter fmtDateCzech = DateTimeFormat.forPattern("dd.MM.yyyy");
    public static final DateTimeFormatter fmtDateTimeCzech = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm");

    @Override
    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    @Override
    public void setFormatPattern(String formatPattern) {
        // JodaTimeFormatter doesn't use format-pattern
    }

    @Override
    public void setLocale(Locale locale) {
        if (this.formatType == null) {
            this.formatType = "date";
        }
    }

    @Override
    /**
     * Sets defaults if a formatType was not supplied.
     */
    public void init() {
        
    }

    @Override
    public String format(DateTime input) {
        if ("date".equals(this.formatType)) {
            return fmtDateCzech.print(input);
        } else if ("dateTimeCzech".equals(this.formatType)) {
            return fmtDateTimeCzech.print(input);
        } else {
            return input.toString();
        }
    }

}
