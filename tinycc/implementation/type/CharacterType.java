package tinycc.implementation.type;

    public class CharacterType extends ScalarType {


        public CharacterType(){
        }

        @Override
        public boolean isInteger(){
            return true;
        }



        @Override
        public String toString() {
            return "Type_char";
        }

}
    

