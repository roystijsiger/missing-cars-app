package com.wherismyvehicle.whereismyvehicle.presenters;

import com.wherismyvehicle.whereismyvehicle.models.User;

public class ProfileFragmentPresenter {
    private User user;
    private View view;

    public ProfileFragmentPresenter(View view) {
        this.view = view;

        // TODO: Load user
        this.user = new User();


    }

    public interface View {
    }
}
