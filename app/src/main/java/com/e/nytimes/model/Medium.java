
package com.e.nytimes.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medium implements Parcelable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("subtype")
    @Expose
    private String subtype;

    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("approved_for_syndication")
    @Expose
    private int approvedForSyndication;
    @SerializedName("media-metadata")
    @Expose
    private List<MediaMetadatum> mediaMetadata = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }


    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getApprovedForSyndication() {
        return approvedForSyndication;
    }

    public void setApprovedForSyndication(int approvedForSyndication) {
        this.approvedForSyndication = approvedForSyndication;
    }

    public List<MediaMetadatum> getMediaMetadata() {
        return mediaMetadata;
    }

    public void setMediaMetadata(List<MediaMetadatum> mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.subtype);
        dest.writeString(this.copyright);
        dest.writeInt(this.approvedForSyndication);
        dest.writeTypedList(this.mediaMetadata);
    }

    public Medium() {
    }

    protected Medium(Parcel in) {
        this.type = in.readString();
        this.subtype = in.readString();
        this.copyright = in.readString();
        this.approvedForSyndication = in.readInt();
        this.mediaMetadata = in.createTypedArrayList(MediaMetadatum.CREATOR);
    }

    public static final Parcelable.Creator<Medium> CREATOR = new Parcelable.Creator<Medium>() {
        @Override
        public Medium createFromParcel(Parcel source) {
            return new Medium(source);
        }

        @Override
        public Medium[] newArray(int size) {
            return new Medium[size];
        }
    };
}
