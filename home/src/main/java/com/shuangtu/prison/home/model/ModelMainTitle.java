package com.shuangtu.prison.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;

public class ModelMainTitle implements Parcelable {

    private String id;
    private String title;

    public ModelMainTitle() {
    }

    public ModelMainTitle(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
    }

    protected ModelMainTitle(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<ModelMainTitle> CREATOR = new Parcelable.Creator<ModelMainTitle>() {
        @Override
        public ModelMainTitle createFromParcel(Parcel source) {
            return new ModelMainTitle(source);
        }

        @Override
        public ModelMainTitle[] newArray(int size) {
            return new ModelMainTitle[size];
        }
    };

    @Override
    public String toString() {
        return "ModelMainTitle{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
