package cz.muni.fi.pa036.betting.extensions;

import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

import net.sourceforge.stripes.validation.DateTypeConverter;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.ValidationError;

public class JodaTimeConverter implements TypeConverter<DateTime> 
{
    DateTypeConverter dateTypeConverter = new DateTypeConverter();

    @Override
    public DateTime convert(String fromVal, Class<? extends DateTime> targetClass, Collection<ValidationError> errors)
    {
        //Date date = dateTypeConverter.convert(fromVal, Date.class, errors);
        //DateTime jodaTime = new DateTime(date);
        DateTime jodaTime = DateTime.parse(fromVal, JodaTimeFormatter.fmtDateTimeCzech);
        return jodaTime;
    }

    @Override
    public void setLocale(Locale locale) {
        dateTypeConverter.setLocale(locale);
    }
    
}