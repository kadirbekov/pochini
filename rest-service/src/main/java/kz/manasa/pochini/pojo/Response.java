package kz.manasa.pochini.pojo;

import java.io.Serializable;

/**
 * Created by dkadirbekov on 04.07.2016.
 */
public class Response implements Serializable {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Response(Object data) {
        this.data = data;
    }
}
