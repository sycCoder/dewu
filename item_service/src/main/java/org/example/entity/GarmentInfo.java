package org.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class GarmentInfo {
    Integer itemId;
    String brand;
    String ingredients;
    Integer pattern;
    Integer thickness;
    String collar;
    String sleeve;
    String masterItemNumber;
    Integer releasePrice;
    String releaseDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String clothingLength;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String sleeveLength;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String graphics;
    String fabric;
    String season;
    String style;
}
