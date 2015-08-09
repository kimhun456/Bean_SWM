package secmem.org.googlemap_hyun;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;


/***************사용법 *************************

        Address_Returner ad = Address_Returner.getInstance();
        String address = ad.get_all_address(getBaseContext(),latitude,longitude);
        String city =  ad.get_city_name(getBaseContext(), latitude, longitude);
        String gu = ad.get_gu_name(getBaseContext(), latitude, longitude);
        address_txt.setText( address + "  "  +  city+ "  " + gu);

 */


public class Address_Returner {

    private static Address_Returner instance =null;
    public static Address_Returner getInstance(){
        if(instance==null){
            instance = new Address_Returner();
        }
        return instance;
    }

    private Address_Returner(){}


    public String get_all_address(Context context, double latitude, double longitude) {

        List<Address> addressList = null;

        String address = " Fail to load Address ";

        try {
            Geocoder gc = new Geocoder(context, Locale.KOREAN);
            addressList = gc.getFromLocation(latitude, longitude, 1);
            Address ad = addressList.get(0);
            address = ad.getAddressLine(0);

        } catch (Exception ex) {
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT)
                    .show();
        }
        return address;
    }


    public String get_city_name(Context context, double latitude, double longitude){
        String result = get_all_address(context,latitude,longitude);

        String address = " Fail to load Address";

        StringTokenizer stringTokenizer = new StringTokenizer(result);
        while(stringTokenizer.hasMoreTokens()){
            String st = stringTokenizer.nextToken();
            int length = st.length();

            if(st.substring(length-1,length).equals("시")){
               return st;
            }
        }

        return address;
    }

    public String get_gu_name(Context context, double latitude, double longitude){
        String result = get_all_address(context,latitude,longitude);

        String address = " Fail to load Address";

        StringTokenizer stringTokenizer = new StringTokenizer(result);
        while(stringTokenizer.hasMoreTokens()){
            String st = stringTokenizer.nextToken();
            int length = st.length();

            if(st.substring(length-1,length).equals("구")){
                return st;
            }
        }

        return address;
    }


    public String get_do_name(Context context, double latitude, double longitude){
        String result = get_all_address(context,latitude,longitude);

        String address = " Fail to load Address";

        StringTokenizer stringTokenizer = new StringTokenizer(result);
        while(stringTokenizer.hasMoreTokens()){
            String st = stringTokenizer.nextToken();
            int length = st.length();

            if(st.substring(length-1,length).equals("도")){
                return st;
            }
        }

        return address;
    }



}
