package br.com.naka.dogs.bean.response;

import br.com.naka.dogs.bean.MessageBean;

public class SigupErrorResponseBean {

    private MessageBean error;

    public MessageBean getError() {
        return error;
    }

    public void setError(MessageBean error) {
        this.error = error;
    }
}
