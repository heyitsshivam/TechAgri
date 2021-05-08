/*
SPDX-License-Identifier: Apache-2.0
*/

package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;

public class ClientApp {

	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}

	public static String main(String[] args) throws Exception {
		String mmm;
		// Load a file system based wallet for managing identities.
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallets.newFileSystemWallet(walletPath);
		// load a CCP
		Path networkConfigPath = Paths.get( "/home/littlstar/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/connection-org1.json");

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {

			// get the network and contract
			Network network = gateway.getNetwork("mychannel");
			Contract contract = network.getContract("track");

			byte[] result;

			result = contract.evaluateTransaction("queryAlltrackproducts");
			System.out.println(new String(result));

			contract.submitTransaction("createneworder", "WYEI3647", "USER456", "Onboard", "fourty five hundred", "5 pieces");

			result = contract.evaluateTransaction("queryOrder", "WYEI3647");
			System.out.println(new String(result));
mmm=new String(result);
			contract.submitTransaction("changeshakeholder", "WYEI3647", "Dealer");

			result = contract.evaluateTransaction("queryOrder", "WYEI3647");
			System.out.println(new String(result));
		}
		return mmm;
	}

}
