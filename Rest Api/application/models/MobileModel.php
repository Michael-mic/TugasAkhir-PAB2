<?php
class MobileModel extends CI_Model {
    function getAllTransaksi() {
        $sql = "SELECT * FROM transaksi ORDER BY created_date ASC";
        $query = $this->db->query($sql);
        return $query->result_array();
    }
    
    function pendingTransaksi() {
        $sql = "SELECT * FROM transaksi WHERE status = 'pending' ORDER BY created_date ASC";
        $query = $this->db->query($sql);
        return $query->result_array();
    }
    
    function doneTransaksi() {
        $sql = "SELECT * FROM transaksi WHERE status = 'done' ORDER BY created_date ASC";
        $query = $this->db->query($sql);
        return $query->result_array();
    }
    
    // function getAllMerek() {
    //     $sql = "SELECT * FROM merek ORDER BY created_date DESC";
    //     $query = $this->db->query($sql);
    //     return $query->result_array();
    // }
    
    function insertTransaksi($data) {
        if($this->db->insert('transaksi', $data)) {
            return TRUE;
        } else {
            return FALSE;
        }
    }
    
    // function insertPost($data) {
    //     if($this->db->insert('barang', $data)) {
    //         return TRUE;
    //     } else {
    //         return FALSE;
    //     }
    // }
    
    function updateTransaksi($data, $where) {
        if($this->db->update('transaksi', $data, $where)) {
            return TRUE;
        } else {
            return FALSE;
        }
    }
    
    function selesaiTransaksi($data, $where) {
        if($this->db->update('transaksi', $data, $where)) {
            return TRUE;
        } else {
            return FALSE;
        }
    }
    
    function deleteTransaksi($where) {
        if($this->db->delete('transaksi', $where)) {
            return TRUE;
        } else {
            return FALSE;
        }
    }
}
?>