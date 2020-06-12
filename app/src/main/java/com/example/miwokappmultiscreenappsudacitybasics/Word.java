package com.example.miwokappmultiscreenappsudacitybasics;

public class Word {

    private String defaultTranslation;
    private String miwokTranslation;
    private int audioResource;
    private int imageResource = HAS_NO_IMAGE;
    private static final int HAS_NO_IMAGE = -1;

    public Word(String defaultTranslation, String miwokTranslation, int imageResource, int audioResource) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.imageResource = imageResource;
        this.audioResource = audioResource;
    }

    public Word(String defaultTranslation, String miwokTranslation, int audioResource) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.audioResource = audioResource;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public int getImageResource() {
        return imageResource;
    }

    public boolean hasImage() {
        return imageResource != HAS_NO_IMAGE;
    }

    public int getAudioResource() {
        return audioResource;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + defaultTranslation + '\'' +
                ", mMiwokTranslation='" + miwokTranslation + '\'' +
                ", mAudioResourceId=" + audioResource +
                ", mImageResourceId=" + imageResource +
                '}';
    }


}
