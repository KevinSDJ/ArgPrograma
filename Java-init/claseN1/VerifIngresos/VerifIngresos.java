package claseN1.VerifIngresos;

import java.math.BigDecimal;

public class VerifIngresos {
    private long ingresMensualNeto;
    private Boolean mayorOIgual3autos;
    private int minAntgVehiculo;
    private int maxAntgVehiculo;
    private Boolean mayorOIguala3Inmuebles;
    /**
     *  posee embarcacion , aereonave o activo societario 
     *
     * @embarAeronaveOActSocietario
     * */
    private Boolean embarAeronaveOActSocietario;

    public VerifIngresos(){
        this.minAntgVehiculo=0;
        this.maxAntgVehiculo=0;
    }

    public VerifIngresos setIngresMensualNeto(long ingresMensualNeto) {
        try {
            this.ingresMensualNeto=ingresMensualNeto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public long gettIngresMensualNeto(){
        return ingresMensualNeto;
    }

    public VerifIngresos setMayorOIgual3autos(Boolean mayorOIgual3autos) {
        this.mayorOIgual3autos=mayorOIgual3autos;
        return this;
    }

    public VerifIngresos setMinAntgVehiculo(int minAntgVehiculo) {
        try {
            this.minAntgVehiculo=minAntgVehiculo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public VerifIngresos setMaxAntgVehiculo(int maxAntgVehiculo) {
        try {
            this.maxAntgVehiculo=maxAntgVehiculo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
        
    }

    public VerifIngresos setMayorOIguala3Inmuebles(Boolean mayorOIguala3Inmuebles) {
        this.mayorOIguala3Inmuebles = mayorOIguala3Inmuebles;
        return this;
    }

    public VerifIngresos setEmbarAeronaveOActSocietario(Boolean embarAeronaveOActSocietario) {
        this.embarAeronaveOActSocietario = embarAeronaveOActSocietario;
        return this;
    }

    public Boolean poseeIngresosAltos(){
        if(
            ingresMensualNeto>=489083
            &&
            mayorOIgual3autos && minAntgVehiculo>=1 && maxAntgVehiculo<=5
            &&
            mayorOIguala3Inmuebles
            &&
            embarAeronaveOActSocietario){
            return true;
        }

        return false;
    }
}
