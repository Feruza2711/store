package uz.pdp.store.dto;
import lombok.*;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T>{
    private Integer code;
    private String message;
    private Boolean status;
    private T data;

    public static<T> ResponseDto<T> buildResponse(T data, Integer code, String message, Boolean status){
        return ResponseDto.<T>builder()
                        .data(data)
                        .code(code)
                        .message(message)
                        .status(status)
                        .build();
    }
}
