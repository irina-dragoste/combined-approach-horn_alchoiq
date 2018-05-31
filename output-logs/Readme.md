This folder contains the output logs of our prototype and of Konclude for the experiments described in the evaluation section of our paper.

Each subfolder (*lubm*, *reactome*, *uniprot*, *uobm*) corresponds to the evaluated ontology partitions. It contains files with the experiment commands and output logs for Konclude reasoner (*-konclude.txt*) and for our prototype impementation that uses RDFox (*-rdfox.txt*). It also contains a file summarizing the time performance of loading and reasoning for each tool (*-summarized.txt*).  

The input files used in the evaluation are available at the following URL: https://www.dropbox.com/sh/w3otk3kn2lj04oy/AADbB9CwMCcdgZFou767vDbSa?dl=0.


We have renamed the input files uploaded at https://www.dropbox.com/home/combined-approach-horn_alchoiq for better readability. 
For example, for Konclude experiments, local file 'LUBM075_hornALCHOIQ.owl' in the output logs corresponds to 'lubm025.owl' in the Dropbox folder.
(More specifically, 
'D:\phd\env\combined-approach\KR\ontos\merged_TboxABox\LUBM\LUBM025_hornALCHOIQ.owl' 
corresponds to 
https://www.dropbox.com/home/combined-approach-horn_alchoiq/evaluation/input-files/Konclude/lubm/lubm025/lubm025.owl .)

For our combined-approach experiments using RDFox, the TBox and ABox are loaded separately, ABox being partitioned into multiple files in the same directory, to take advantage of RDFox's parallel capabilities.
TBox local file 'normalized_minCard_manually_trimmed_LUBM.owl' in the output logs corresponds to 'lubm-TBox.owl' in the Dropbox folder.
ABox local files folder 'LUBM025' in the output logs corresponds to 'lubm-ABox' in the Dropbox folder.
(More specifically, 
'..\ontos\3_TBoxes_Normalized_minCard_Trimed_HornAlchoiq\normalized_minCard_manually_trimmed_LUBM.owl'
corresponds to  
'https://www.dropbox.com/home/combined-approach-horn_alchoiq/evaluation/input-files/rdfox/lubm/lubm-TBox.owl'

and
'..\ontos\ABoxes\LUBM\AcyReasoning\LUBM025'
corresponds to  
'https://www.dropbox.com/home/combined-approach-horn_alchoiq/evaluation/input-files/rdfox/lubm/lubm-ABox/LUBM025' .)
