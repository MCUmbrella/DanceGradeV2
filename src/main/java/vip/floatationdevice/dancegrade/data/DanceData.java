package vip.floatationdevice.dancegrade.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DanceData
{
    @JsonIgnore // ignore setting ID in deserialization as it's not needed
    private Long id;
    @NotNull(message = "'studentId' cannot be null")
    @Positive(message = "'studentId' must be positive")
    private Long studentId;
    @NotNull(message = "'name' cannot be null")
    @NotBlank(message = "'name' cannot be empty")
    private String name;
    @NotNull(message = "'actions' cannot be null")
    private int[] actions;
    @NotNull(message = "'scores' cannot be null")
    private double[] scores;
    @NotNull(message = "'scoreAvg' cannot be null")
    @Positive(message = "'scoreAvg' must be positive")
    private Double scoreAvg;

    @JsonProperty // don't ignore ID when converting to JSON
    public Long getId(){return id;}
}
