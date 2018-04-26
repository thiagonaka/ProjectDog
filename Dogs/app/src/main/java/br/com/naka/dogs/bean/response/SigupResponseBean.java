package br.com.naka.dogs.bean.response;

import br.com.naka.dogs.bean.UserBean;
import br.com.naka.dogs.bean.request.SigupRequestBean;

public class SigupResponseBean extends SigupErrorResponseBean {

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
