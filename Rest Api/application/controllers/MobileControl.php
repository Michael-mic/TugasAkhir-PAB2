<?php
class MobileControl extends CI_Controller {
    function __Construct() {
        parent::__Construct();
        $this->load->Model('MobileModel');
    }
    
    function getAllTransaksi() {
        $key = $this->input->post('key', TRUE);
        $status = array();
        
        if($key == 'yeslaundry') {
            $status['status'] = TRUE;
            $status['data'] = $this->MobileModel->getAllTransaksi();
            $status['message'] = "Retrieve Data Success!";
        } else {
            $status['status'] = FALSE;
            $status['message'] = "Invalid Key!";
        }
        $this->load->view('MobileView/ViewData', $status);
    }
    
    function pendingTransaksi() {
        $key = $this->input->post('key', TRUE);
        $status = array();
        
        if($key == 'yeslaundry') {
            $status['status'] = TRUE;
            $status['data'] = $this->MobileModel->pendingTransaksi();
            $status['message'] = "Retrieve Data Success!";
        } else {
            $status['status'] = FALSE;
            $status['message'] = "Invalid Key!";
        }
        $this->load->view('MobileView/ViewData', $status);
    }
    
    function doneTransaksi() {
        $key = $this->input->post('key', TRUE);
        $status = array();
        
        if($key == 'yeslaundry') {
            $status['status'] = TRUE;
            $status['data'] = $this->MobileModel->doneTransaksi();
            $status['message'] = "Retrieve Data Success!";
        } else {
            $status['status'] = FALSE;
            $status['message'] = "Invalid Key!";
        }
        $this->load->view('MobileView/ViewData', $status);
    }
    
    // function getAllMerek() {
    //     $key = $this->input->post('key', TRUE);
    //     $status = array();
        
    //     if($key == 'dirumahaja') {
    //         $status['status'] = TRUE;
    //         $status['data'] = $this->MobileModel->getAllMerek();
    //         $status['message'] = "Retrieve Data Success!";
    //     } else {
    //         $status['status'] = FALSE;
    //         $status['message'] = "Invalid Key!";
    //     }
    //     $this->load->view('MobileView/ViewData', $status);
    // }
    
    function insertTransaksi() {
        $key = $this->input->post('key', TRUE);
        $namapelanggan = $this->input->post('namapelanggan', TRUE);
        $nohp = $this->input->post('nohp', TRUE);
        $alamat = $this->input->post('alamat', TRUE);
        $kg = $this->input->post('kg', TRUE);
        $jenis = $this->input->post('jenis', TRUE);
        $harga = $this->input->post('harga', TRUE);
        
        $data = array();
        $data['namapelanggan'] = $namapelanggan;
        $data['nohp'] = $nohp;
        $data['alamat'] = $alamat;
        $data['kg'] = $kg;
        $data['jenis'] = $jenis;
        $data['harga'] = $harga;
        
        $status = array();
        
        if($key == 'yeslaundry') {
            if($this->MobileModel->insertTransaksi($data)) {
                $status['status'] = TRUE;
                $status['message'] = "Insert Transaksi Successfully";
            } else {
                $status['status'] = FALSE;
                $status['message'] = "Insert Transaksi Failed";
            }
        } else {
            $status['status'] = FALSE;
            $status['message'] = "Invalid Key!";
        }
        $this->load->view('MobileView/ViewNoData', $status);
    }
    
    // function insertPost() {
    //     $key = $this->input->post('key', TRUE);
    //     $merek = $this->input->post('merek', TRUE);
    //     $namabarang = $this->input->post('namabarang', TRUE);
    //     $harga = $this->input->post('harga', TRUE);
        
    //     $merek_data = $this->MobileModel->getSingleMerek($merek);
        
    //     $data = array();
    //     $data['namabarang'] = $namabarang;
    //     $data['harga'] = $harga;
    //     $data['merek_id'] = $merek_data['id'];
        
    //     $status = array();
        
    //     if($key == 'dirumahaja') {
    //         if($this->MobileModel->insertPost($data)) {
    //             $status['status'] = TRUE;
    //             $status['message'] = "Insert Post Successfully";
    //         } else {
    //             $status['status'] = FALSE;
    //             $status['message'] = "Insert Post Failed";
    //         }
    //     } else {
    //         $status['status'] = FALSE;
    //         $status['message'] = "Invalid Key!";
    //     }
    //     $this->load->view('MobileView/ViewNoData', $status);
    // }
    
    function updateTransaksi() {
        $key = $this->input->post('key', TRUE);
        $id = $this->input->post('id', TRUE);
        $namapelanggan = $this->input->post('namapelanggan', TRUE);
        $nohp = $this->input->post('nohp', TRUE);
        $alamat = $this->input->post('alamat', TRUE);
        $kg = $this->input->post('kg', TRUE);
        $jenis = $this->input->post('jenis', TRUE);
        $harga = $this->input->post('harga', TRUE);
        $status = $this->input->post('status', TRUE);
        
        $data = array();
        $data['namapelanggan'] = $namapelanggan;
        $data['nohp'] = $nohp;
        $data['alamat'] = $alamat;
        $data['kg'] = $kg;
        $data['jenis'] = $jenis;
        $data['harga'] = $harga;
        $data['status'] = $status;
        
        $where = array('id' => $id);
        
        $status = array();
        
        if($key == 'yeslaundry') {
            if($this->MobileModel->updateTransaksi($data, $where)) {
                $status['status'] = TRUE;
                $status['message'] = "Update Post Successfully";
            } else {
                $status['status'] = FALSE;
                $status['message'] = "Update Post Failed";
            }
        } else {
            $status['status'] = FALSE;
            $status['message'] = "Invalid Key!";
        }
        $this->load->view('MobileView/ViewNoData', $status);
    }
    
    function deleteTransaksi() {
        $key = $this->input->post('key');
        $id = $this->input->post('id', TRUE);
        
        $where = array('id' => $id);
        
        $status = array();
        
        if($key == 'yeslaundry') {
            if($this->MobileModel->deleteTransaksi($where)) {
                $status['status'] = TRUE;
                $status['message'] = "Delete Post Successfully";
            } else {
                $status['status'] = FALSE;
                $status['message'] = "Delete Post Failed";
            }
        } else {
            $status['status'] = FALSE;
            $status['message'] = "Invalid Key!";
        }
        $this->load->view('MobileView/ViewNoData', $status);
    }
}
?>