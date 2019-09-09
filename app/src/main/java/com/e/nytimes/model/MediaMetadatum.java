
package com.e.nytimes.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaMetadatum implements Parcelable {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("width")
    @Expose
    private int width;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.format);
        dest.writeInt(this.height);
        dest.writeInt(this.width);
    }

    public MediaMetadatum() {
    }

    protected MediaMetadatum(Parcel in) {
        this.url = in.readString();
        this.format = in.readString();
        this.height = in.readInt();
        this.width = in.readInt();
    }

    public static final Parcelable.Creator<MediaMetadatum> CREATOR = new Parcelable.Creator<MediaMetadatum>() {
        @Override
        public MediaMetadatum createFromParcel(Parcel source) {
            return new MediaMetadatum(source);
        }

        @Override
        public MediaMetadatum[] newArray(int size) {
            return new MediaMetadatum[size];
        }
    };
}
