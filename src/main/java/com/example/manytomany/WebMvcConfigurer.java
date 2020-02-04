package com.example.manytomany;

import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.xslt.XsltViewResolver;

/**
 * @author P-C Lin (a.k.a 高科技黑手)
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
		resourceHandlerRegistry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {
		defaultServletHandlerConfigurer.enable();
	}

	@Bean
	public ViewResolver getResolver() {
		XsltViewResolver xsltViewResolver = new XsltViewResolver();
		xsltViewResolver.setIndent(false);
		xsltViewResolver.setPrefix("classpath:/templates/");
		xsltViewResolver.setSuffix(".xsl");
//		xsltViewResolver.setUriResolver(new URIResolver());

		return xsltViewResolver;
	}

	@Bean
	public FormattingConversionService conversionService() {
		DefaultFormattingConversionService defaultFormattingConversionService = new DefaultFormattingConversionService(false);

		DateTimeFormatterRegistrar dateTimeFormatterRegistrar = new DateTimeFormatterRegistrar();
		dateTimeFormatterRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		dateTimeFormatterRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		dateTimeFormatterRegistrar.registerFormatters(defaultFormattingConversionService);

		return defaultFormattingConversionService;
	}
}
