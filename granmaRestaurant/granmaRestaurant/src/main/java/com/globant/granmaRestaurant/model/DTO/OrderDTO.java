package com.globant.granmaRestaurant.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
        private String uuid;
        private String customerDocument;
        private String comboUuid;
        private Integer quantity;
        private String extraInformation;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        private LocalDateTime creationDateTime;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
        private Double subtotal;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
        private Double vatTax;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
        private Double grandTotal;
        private Boolean delivered;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        private LocalDateTime deliveredDate;
}
