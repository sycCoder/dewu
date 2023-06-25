package org.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
public class ShoeInfo {
    Integer itemId;
    String brand;
    String masterItemNumber;
    Integer releasePrice;
    String releaseDate;
    String upperMaterial;
    String soleMaterial;
    String toeStyle;
    String heelType;
    String upperHeight;
    String functionality;
    String tone;
    String packingList;
    String season;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String colors;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String mainColor;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String secondaryColor;
}
