<?php

if(isset($status)) {
    if($status) {
        $response['success'] = 1;
        $response['message'] = "Operation Success!";
    } else {
        $response['success'] = 0;
        $response['message'] = "Operation Failed!";
    }
} else {
    $response['success'] = 0;
    $response['message'] = "No POST Data!";
}

echo json_encode($response);
?>