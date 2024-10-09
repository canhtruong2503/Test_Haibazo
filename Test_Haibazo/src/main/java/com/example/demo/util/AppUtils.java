package com.example.demo.util;

import com.example.demo.model.Color;
import com.example.demo.model.Image;
import com.example.demo.model.Product;
import com.example.demo.model.Size;
import com.example.demo.service.product.response.ProductResponse;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppUtils {
    public static final ModelMapper mapper;
    static {
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.getConfiguration().setFieldMatchingEnabled(true);
        Converter<String, LocalDate> toStringDate = new AbstractConverter<>() {
            @Override
            protected LocalDate convert(String source) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(source, format);
            }
        };
        mapper.createTypeMap(String.class, LocalDate.class);
        mapper.addConverter(toStringDate);
    }
    public static final ProductResponse mapperProductToDto(Product product) {
        ProductResponse productResponse= mapper.map(product, ProductResponse.class);
        List<Image> images = product.getImages();
        List<ProductResponse.ImgResponse> imgResponse = images.stream()
                .map(i -> {
                    return   AppUtils.mapper.map(i, ProductResponse.ImgResponse.class);
                }).toList();
        List<Size> sizes = product.getSizes();
        List<ProductResponse.SizeResponse> sizeResponses = sizes.stream()
                .map(i -> {
                    return   AppUtils.mapper.map(i, ProductResponse.SizeResponse.class);
                }).toList();
        List<Color> colors = product.getColors();
        List<ProductResponse.ColorResponse> colorResponses = colors.stream()
                .map(i -> {
                    return   AppUtils.mapper.map(i, ProductResponse.ColorResponse.class);

                }).toList();
        productResponse.setSizes(sizeResponses);
        productResponse.setImages(imgResponse);
        productResponse.setColors(colorResponses);
        return productResponse;
    }
}
