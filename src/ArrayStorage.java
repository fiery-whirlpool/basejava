/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
        }
    }

    void save(Resume r) {
        for(int i=0;i<storage.length;i++){
            if (storage[i]==null){
                storage[i]= r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        int idx=0;
        for(int i=0; i<storage.length;i++) {
            if(storage[i] != null) {
                if(storage[i].uuid == uuid){
                    idx = i;
                    break;
                }
            }else
                break;
        }
         return storage[idx];
     }


    void delete(String uuid) {
        int count = 0;
        for(int i=0; i<storage.length;i++) {
            if (storage[i] != null) {
                count ++;
                if (storage[i].uuid == uuid) {
                    for (int j = i; j < storage.length; j++) {
                        if (storage[j] != null && storage[j + 1] != null) {
                            storage[j] = storage[j + 1];
                        }
                    }
                }
            }
        }
        storage[count-1]=null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int count=0;
        for(int i=0; i<storage.length; i++){
            if(storage[i] != null){
              count ++;
            }
        }
        Resume[] res = new Resume[count];
        for(int i=0; i<count;i++){
            res[i] = storage[i];
        }
        return res;
    }

    int size() {
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                count++;
            } else break;
        }
        return count;

    }
}