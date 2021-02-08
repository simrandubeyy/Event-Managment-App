package com.example.signup;

public class Upload {
    private String mName;
    private String mEventName;
    private int mDate;
    private int mTime;
    private String mAddress;
    private int mContactNumber;
    private String mImageUrl;

    public Upload(String trim, String eventName, String s, String trim1, String address, String s1, String imageUrl)
    {
       // Empty
    }

    public Upload(String name, String eventName, int date,
                  int time, String address, int contactNumber,
                  String imageUrl)
    {
        if(name.trim().equals(""))
        {
            name="No Name";
        }

        if(eventName.trim().equals(""))
        {
            name="No Event Name";
        }

        if(address.trim().equals(""))
        {
            name="No Event address";
        }

        mName = name;
        mEventName = eventName;
        mDate = date;
        mTime = time;
        mAddress = address;
        mContactNumber = contactNumber;
        mImageUrl = imageUrl;
    }

    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }



    public String getEventName()
    {
        return mEventName;
    }

    public void setEventName(String eventName)
    {
        mEventName = eventName;
    }



    public String getAddress()
    {
        return mAddress;
    }

    public void setAddress(String address)
    {
        mAddress = address;
    }



    public int getDate()
    {
        return mDate;
    }

    public void setDate(int date)
    {
        mDate = date;
    }


    public int getTime()
    {
        return mTime;
    }

    public void setmTime(int time)
    {
        mTime = time;
    }



    public int getContactNumber()
    {
        return mDate;
    }

    public void setContactNumber(int contactNumber)
    {
        mContactNumber = contactNumber;
    }

    public String getImageUrl()
    {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        mImageUrl = imageUrl;
    }

}
