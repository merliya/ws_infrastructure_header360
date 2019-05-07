package com.jbhunt.infrastructure.header360.configuration;

import com.github.ziplet.filter.compression.CompressingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfiguration {

	@Bean
	public FilterRegistrationBean filterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(compressionFilter());
		registration.setName("compressionFilter");
		registration.setOrder(1);
		return registration;
	}

	/**
	 * the compressing filter will gzip web service responses
	 *
	 * @return CompressingFilter
	 */
	@Bean
	public Filter compressionFilter() {
		return new CompressingFilter();
	}

	@Bean
	public WebMvcConfigurerAdapter webConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {

				registry.setOrder(2).addResourceHandler("/**").addResourceLocations("classpath:/public/")
						.setCacheControl(CacheControl.noCache());
			}
		};
	}
	@Bean
	public Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}

	@Bean
	public FilterRegistrationBean shallowEtagHeaderFilterRegistration() {
		FilterRegistrationBean result = new FilterRegistrationBean();
		result.setFilter(this.shallowEtagHeaderFilter());
		result.addUrlPatterns("/*");
		result.setName("shallowEtagHeaderFilter");
		result.setOrder(1);
		return result;
	}
}
