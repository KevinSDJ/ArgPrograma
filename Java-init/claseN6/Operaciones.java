public abstract class Operaciones {



    protected int[] parsearAEnteros(String line) {
        int[] nums= new int[line.split(" ").length];
        int i = 0;
        for (String num : line.split(" ")) {
            nums[i] = Integer.parseInt(num);
            i++;
        }
        return nums;
    }

    protected int sum(int... args){
        if(args.length<2){
            return args[0];
        }
        int acc=0;
        for(int numero:args){
            acc+=numero;
        }
        return acc;
    }

    protected int multiplicacion(int... args){
        if(args.length<2){
            return args[0];
        }
        int acc=0;
        for(int numero:args){
            if(acc==0){
                acc=numero;
            }else{
                acc*=numero;
            }
        }
        return acc;
    }
    protected int resta(int... args){
        if(args.length<2){
            return args[0];
        }
        int acc=0;
        for(int numero:args){
            if(acc==0){
                acc=numero;
            }else{
                acc-=numero;
            }
        }
        return acc;
        
    }
    
    protected float division(int... args){
        if(args.length<2){
            return args[0];
        }
        float r= (float) args[0]/args[1];
        System.out.println(r);
        return r;
    }
}
