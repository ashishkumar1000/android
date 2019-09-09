
package com.e.nytimes.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MediaMetadatum implements Parcelable {
    private String url;
    private String format;
    private int height;
    private int width;


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
