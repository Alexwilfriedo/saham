<?php
        
         $doc = file_get_contents('listrachat.json'); 
         $decode = json_decode($doc, true); 
		 
         $addrachat['nom'] = $_POST['nom'];
         $addrachat['contrat'] = $_POST['contrat'];
         $addrachat['lieu'] = $_POST['lieu'];
		 $addrachat['montant'] = $_POST['montant'];
		 
         array_push( $decode['items'], $addrachat); 
         $doc = json_encode($decode);
         
         if (json_decode($doc) != null)
           {
             $file = fopen('listrachat.json','w');
             fwrite($file, $doc);
             fclose($file);
           }
           else
           {
             echo 'erreur code'; 
           }

        ?>