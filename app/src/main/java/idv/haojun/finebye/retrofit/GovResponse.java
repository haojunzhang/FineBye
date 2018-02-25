package idv.haojun.finebye.retrofit;

public class GovResponse<T> {
    private boolean success;
    private GovResult<T> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public GovResult<T> getResult() {
        return result;
    }

    public void setResult(GovResult<T> result) {
        this.result = result;
    }
}
