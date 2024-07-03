package Consultor.Fipe.com.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record MarcaDados
        (
                @SerializedName("code")
                @JsonAlias("code")
                String codMarca,

                @SerializedName ("name")
                @JsonAlias ("name")
                String nomeMarca
        )
{

}
