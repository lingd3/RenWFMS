package org.sysu.renZuul.filter;

import com.netflix.zuul.ZuulFilter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Author: Gordan
 * Date  : 2018/3/14
 * Usage : Zuul Filter.
 */
@Component
public class PostFilter extends ZuulFilter {

    private static Logger log = Logger.getLogger(AccessFilter.class);
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        log.info(String.format("%s >>> %s", "request end" , sdf.format(new Date())));
        return null;
    }
}
