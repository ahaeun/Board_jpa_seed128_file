package com.he.board.vo.BoardVO;

import java.time.LocalDateTime;

import javax.management.ConstructorParameters;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "board")
public class BoardVO {

    @Id
    @Column(name="BoardID")
    private int boardID;

    @Column(name="BoardTitle")
    private String boardTitle;

    @Column(name="BoardContent")
    private String boardContent;

    @Column(name="CreateAt")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "UpdateAt")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Column(name = "IsDelete")
    private String isDelete;

    @Column(name = "FileOriginalName")
    private String fileOriginalName;

    @Column(name = "FileEncryptName")
    private String fileEncryptName;

    @Column(name = "FileSize")
    private String fileSize;

    @Column(name = "FileMimeType")
    private String fileMimeType;

    @Column(name = "FileExtension")
    private String fileExtesion;

    @ConstructorParameters({"boardVO", "file", "enc", "fileExtension"})
    public BoardVO(BoardVO boardVO, MultipartFile file, String enc, String fileExtension){
        this.setFileOriginalName(file.getOriginalFilename());
        this.setFileEncryptName(enc);
        this.setFileSize(file.getSize()+"");
        this.setFileExtesion(file.getContentType());
        this.setBoardTitle(boardVO.getBoardTitle());
        this.setBoardContent(boardVO.getBoardContent());
        this.setFileMimeType(file.getContentType());
        this.setFileExtesion(fileExtension);
        this.setIsDelete("N");
    }

    public BoardVO(){}


}
